import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String args[]) throws MyException {
        System.out.println("Клиент совершает покупку онлайн. При оформлении заказа у пользователя" +
                "\nзапрашивается фио и инн. в программе проверяется действителен ли номер ИНН для такого клиента." +
                "\nИсключение будет выдано в том случае, если введен недействительный ИНН");
        //считывание файла
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("data_for_19.txt"));//проверка существует ли такой файл вообще
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = new String();
        ArrayList<String> words = new ArrayList<>();
        String words_buf[];
        while (scanner.hasNextLine()) {//если существуюет следующая строка
            line = scanner.nextLine();//то прочитаем её и разделим на слова с помощью пробелов
            words_buf = line.split(" ");//таким образом, получим массив words, где: 0,4,8...- Фамилия
            for (String i : words_buf) {//1,5,9...- Имя, 2,6,10...-Отчество, 3,7,11...-ИНН
                words.add(i);
            }
        }
        //вывод базы данных
        for (int i = 0; i < words.toArray().length / 4; i++) {
            System.out.printf("%d ) %10s %10s %10s %14s \n", i + 1, words.get(0 + (i * 4)), words.get(1 + (i * 4)), words.get(2 + i * 4), words.get(3 + i * 4));
        }
        //разделение массива words на отдельные массивы
        String name[] = new String[words.toArray().length / 4];
        String surname[] = new String[words.toArray().length / 4];
        String second_name[] = new String[words.toArray().length / 4];
        String INN[] = new String[words.toArray().length / 4];
        for (int i = 0; i < words.toArray().length / 4; i++) {
            surname[i] = words.get(0 + (i * 4));
            name[i] = words.get(1 + (i * 4));
            second_name[i] = words.get(2 + (i * 4));
            INN[i] = words.get(3 + i * 4);
        }
        //проверка ИНН
        Found data = new Found();
        Scanner information = new Scanner(System.in);
        System.out.println("Введите свое имя");
        String name_of_client = information.nextLine();
        System.out.println("Введите свою фамилию");
        String surname_of_client = information.nextLine();
        System.out.println("Введите отчество");
        String second_name_of_client = information.nextLine();
        System.out.println("Введите свой ИНН");
        String INN_of_client = information.nextLine();
        data.setINN(INN_of_client);
        data.setName(name_of_client);
        data.setSurname(surname_of_client);
        data.setSecond_name(second_name_of_client);
        data.result(data.find(name, surname, second_name, INN));
    }
}