package uz.pdp;

import uz.pdp.History.History;
import uz.pdp.History.PaymentHistory;
import uz.pdp.model.*;
import uz.pdp.service.*;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserService();
        CardService cardService = new CardService();
        ValletService valletService = new ValletService();
        HistoryService historyService = new HistoryService();
        CategoryService categoryService = new CategoryService();
        ServiceService serviceService = new ServiceService();
        PaymentHistoryService paymentHistoryService = new PaymentHistoryService();
        FieldService fieldService = new FieldService();
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {

            System.out.println("1.Sign In\t 2.Sign Up");
            int b = scannerInt.nextInt();
            if (b == 1) {
                User currentUser = signIn(scannerInt, scannerStr, userService);
                if (currentUser != null) {
                    if (currentUser.isAdmin())
                        adminMenu(scannerInt, scannerStr, userService, categoryService, fieldService, serviceService);
                    else {
                        int s = 1;
                        while (s != 0) {
                            System.out.println("1.Karta qo'shish\t 2.Kartani to'ldirish\t 3.Pul o'tkazish \t 4.CashBack \t 5.History\t 6.Balans\t 7.Payment \t 0.EXIT");
                            switch (scannerInt.nextInt()) {
                                case 0:
                                    s = 0;
                                    break;
                                case 1:
                                    Card card1 = new Card();
                                    System.out.println("karta nomini kiriting");
                                    card1.setName(scannerStr.nextLine());
                                    System.out.println("karta raqamini kiriting");
                                    card1.setCardNumber(scannerInt.nextInt());
                                    card1.setPhoneNumber(currentUser.getPhoneNumber());
                                    card1.setUserId(currentUser.getId());
                                    cardService.add(card1);
                                    break;

                                case 2:
                                    boolean a = cardService.checkCard(currentUser.getPhoneNumber());
                                    if (a) {
                                        List<Card> cardList = cardService.getList();
                                        int i = 1;
                                        for (Card card : cardList) {
                                            if (card.getPhoneNumber() == currentUser.getPhoneNumber())
                                                System.out.println((i++) + ". " + card.getCardNumber() + " | " + card.getName() + " | " + card.getCash());
                                        }
                                        System.out.println("kartani tanlang \t 0.EXIT");
                                        int c = scannerInt.nextInt();
                                        if (c == 0) break;
                                        Card currentCard = cardService.getCard(c, currentUser.getPhoneNumber());
                                        System.out.println("summani kiriting");
                                        cardService.fullCard(scannerInt.nextDouble(), currentCard);
                                    }
                                    break;

                                case 3:
                                    List<Card> cardList = cardService.getList();
                                    int i = 1;
                                    for (Card card : cardList) {
                                        if (card.getPhoneNumber() == currentUser.getPhoneNumber())
                                            System.out.println((i++) + ". " + card.getCardNumber() + " | " + card.getName() + " | " + card.getCash());
                                    }
                                    System.out.println("Qaysi kartadan pul otkazmoqchisiz, kartani tanlang\t 0.EXIT");
                                    int c = scannerInt.nextInt();
                                    if (c == 0) break;
                                    Card currentCard = cardService.getCard(c, currentUser.getPhoneNumber());
                                    System.out.println("qabul qiluvchini karta raqamini kiriting");
                                    int cardNumber = scannerInt.nextInt();
                                    Card cardId = cardService.getCardId(cardNumber);
                                    if (cardId != null) {
                                        System.out.println("summani kiriting");
                                        double sum = scannerInt.nextDouble();
                                        System.out.println("comissiya = " + sum * UserService.commission / 100 + "(" + UserService.commission + "%)");
                                        double comm = (100 + UserService.commission) / 100;
                                        if ((currentCard.getCash() - sum * comm >= 0)) {
                                            userService.p2P(currentCard, cardId.getCardId(), sum, currentUser.getPhoneNumber());
                                            Vallet vallet = new Vallet();
                                            vallet.setPhoneNumber(currentUser.getPhoneNumber());
                                            vallet.setAmount(sum * UserService.cashBack / 100);
                                            valletService.addVallet(vallet);

                                            String str1 = userService.getUserName(cardId.getCardId());
                                            String str2 = userService.getUserName(currentCard.getCardId());

                                            History history = new History(currentCard.getUserId(), cardId.getUserId(), "CHIQIM", sum, str1, str2);
                                            historyService.addHistory(history);

                                            History history1 = new History(cardId.getUserId(), currentCard.getUserId(), "KIRIM", sum, str1, str2);
                                            historyService.addHistory(history1);

                                            System.out.println("O'tkazma muvaffaqiyatli amalga oshirildi!!!");
                                        } else
                                            System.out.println("Mablag yetarli emas");

                                    } else
                                        System.out.println("Bundey karta raqam mavjud emas");
                                    break;

                                case 4:

                                    System.out.println("CashBack = " + UserService.cashBack + "(%) dan");
                                    double cashBack = valletService.getCashBack(currentUser.getPhoneNumber());
                                    System.out.println("Bonus = " + cashBack);
                                    break;

                                case 5:
                                    historyService.getHistory(currentUser.getId());
                                    System.out.println("---------------------------------------------------------------");

                                    System.out.println("---------------> Payment history <----------------");
                                    paymentHistoryService.getHistory(currentUser.getId());
                                    break;

                                case 6:
                                    List<Card> cardList3 = cardService.getList();
                                    int i3 = 1;
                                    for (Card card : cardList3) {
                                        if (card.getPhoneNumber() == currentUser.getPhoneNumber())
                                            System.out.println((i3++) + ". " + card.getCardNumber() + " | " + card.getName() + " | " + card.getCash());
                                    }
                                    break;

                                case 7:
                                    List<ServiceCategory> list = categoryService.getList();
                                    if (list.size() == 0) break;
                                    for (int j = 0; j < list.size(); j++) {
                                        System.out.println((j + 1) + ". " + list.get(j).getName().toUpperCase());
                                    }

                                    System.out.println("kategoriyani tanlang");

                                    long currentCategoryServiceId = categoryService.getCategoryId(scannerInt.nextInt());

                                    int i1 = 1;
                                    for (Service service : serviceService.getList()) {
                                        if (service.getCategoryId() == currentCategoryServiceId)
                                            System.out.println((i1++) + ". " + service.getName());
                                    }

                                    System.out.println("service ni tanlang");

                                    long serviceId = serviceService.getServiceId(scannerInt.nextInt(), currentCategoryServiceId);
                                    PaymentHistory paymentHistory = new PaymentHistory();
                                    String sum = "";
                                    for (int j = 0; j < fieldService.getList().size(); j++) {
                                        if (fieldService.getList().get(j).getServiceId() == serviceId) {
                                            System.out.print(fieldService.getList().get(j).getTitle().toUpperCase() + " : ");
                                            paymentHistory.setKey(fieldService.getList().get(j).getName());
                                            sum = scannerStr.nextLine();
                                            paymentHistory.setValue(sum);
                                        }

                                    }
                                    paymentHistory.setUserId(currentUser.getId());

                                    for (Service service : serviceService.getList()) {
                                        if (service.getId() == serviceId)
                                            paymentHistory.setServiceName(service.getName());
                                    }

                                    System.out.println("-------------------------------------------------");
                                    List<Card> list1 = cardService.getList();
                                    if (list1.size() > 0) {
                                        int k = 1;
                                        for (int j = 0; j < list1.size(); j++) {
                                            if (list1.get(j).getPhoneNumber() == currentUser.getPhoneNumber()) {
                                                System.out.println((k++) + ". karta nomeri:" + list1.get(j).getCardNumber() + " | karta nomi : " + list1.get(j).getName() + " | mablag : " + list1.get(j).getCash());
                                            }
                                        }
                                    } else {
                                        System.out.println("sizda karta mavjud emas!!!");
                                        break;
                                    }

                                    System.out.println("karta tanlang");
                                    Card currentCard1 = cardService.getCard(scannerInt.nextInt(), currentUser.getPhoneNumber());
                                    paymentHistory.setCardNumber(currentCard1.getCardNumber());
                                    paymentHistoryService.add(paymentHistory);
                                    cardService.paymentCash(currentCard1.getCardId(), Double.parseDouble(sum));

                                    Vallet vallet = new Vallet();
                                    vallet.setName(categoryService.getCategoryName(currentCategoryServiceId));
                                    vallet.setPhoneNumber(currentUser.getPhoneNumber());
                                    vallet.setAmount(vallet.getAmount() + Double.parseDouble(sum) * UserService.cashBack / 100);
                                    valletService.addVallet(vallet);
                                    System.out.println("To'lov muvaffaqiyatli amalga oshirildi");
                                    break;
                            }
                        }
                    }

                }

            }
            if (b == 2) signUp(scannerInt, scannerStr, userService);
        }

    }

    public static void adminMenu(Scanner scannerInt, Scanner scannerStr,
                                 UserService userService, CategoryService categoryService,
                                 FieldService fieldService, ServiceService serviceService) {
        Scanner scanner = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1.UserList\t 2.Transaction\t 3.System Balance \t 4.Add Category \t 5.Add Service\t 0.EXIT");
            switch (scanner.nextInt()) {
                case 0:
                    stepCode = 0;
                    break;

                case 1:
                    System.out.println("User lar ro'yhati :");
                    List<User> userList = userService.getList();
                    int i = 1;
                    for (User user : userList) {
                        System.out.println((i++) + ")" + user.getUserName() + " | " + user.getPhoneNumber());
                    }
                    break;

                case 2:
                    System.out.println("Commission : " + UserService.commission + "%");
                    System.out.println("CashBack : " + UserService.cashBack + "%");
                    System.out.println("1.Commission edit\t 2.CashBack edit\t 0.Exit");
                    int n = scannerInt.nextInt();
                    if (n == 1) {
                        System.out.println("enter new commission (%)");
                        userService.setCommission(scannerInt.nextDouble());
                    } else if (n == 2) {
                        System.out.println("enter new cashBack (%)");
                        userService.setCashBack(scannerInt.nextDouble());
                    } else break;
                    break;

                case 3:
                    System.out.println("System balance = " + UserService.systemBalance);
                    break;

                case 4:
                    ServiceCategory serviceCategory = new ServiceCategory();
                    System.out.println("category name kiriting");
                    serviceCategory.setName(scannerStr.nextLine());
                    categoryService.add(serviceCategory);
                    System.out.println("Category muvaffaqiyatli qo'shildi!!!");
                    break;

                case 5:
                    if (categoryService.getList().size() > 0) {
                        int i1 = 1;
                        for (ServiceCategory serviceCategory1 : categoryService.getList()) {
                            System.out.println((i1++) + ")" + serviceCategory1.getName().toUpperCase());
                        }
                        System.out.println("Categoriyani tanlang");

                        long currentCategoryServiceId = categoryService.getCategoryId(scannerInt.nextInt());

                        System.out.println("servis nomini kiriting");

                        Service service = new Service();

                        service.setName(scannerStr.nextLine());
                        service.setCategoryId(currentCategoryServiceId);
                        serviceService.add(service);

                        System.out.println("nechta field qo'shasiz");
                        int m = scannerInt.nextInt();
                        for (int j = 0; j < m; j++) {
                            ServiceField serviceField = new ServiceField();
                            serviceField.setServiceId(service.getId());
                            System.out.print((j + 1) + " - title kiriting = ");
                            serviceField.setTitle(scannerStr.nextLine());
                            System.out.print("name ni kiriting : ");
                            serviceField.setName(scannerStr.nextLine());
                            fieldService.add(serviceField);
                        }
                        System.out.println("service muvaffaqiyatli qo'shildi!!!");
                    } else
                        System.out.println("Categoriyalar mavjud emas.");
                    break;
            }
        }
    }

    public static User signIn(Scanner scannerInt, Scanner scannerStr, UserService userService) {
        System.out.println("userName kiriting");
        String userName = scannerStr.nextLine();
        System.out.println("parolni kiriting");
        String password = scannerStr.nextLine();
        User currentUser = userService.checkUser(userName, password);
        if (currentUser != null)
            return currentUser;
        else System.out.println("user not found");
        return null;
    }

    public static void signUp(Scanner scannerInt, Scanner scannerStr, UserService userService) {
        User user = new User();
        System.out.println("telefon nomeringizni kiriting");
        user.setPhoneNumber(scannerInt.nextInt());
        System.out.println("Name kiriting");
        user.setUserName(scannerStr.nextLine());
        System.out.println("userName kiriting");
        user.setUserName(scannerStr.nextLine());
        System.out.println("parolni kiriting");
        user.setPassword(scannerStr.nextLine());
        userService.add(user);
    }

    public static void payment() {

    }
}
