package com.example.crud_demo;

import com.example.crud_demo.models.Store;
import com.example.crud_demo.models.User;
import com.example.crud_demo.repositories.StoreRepository;
import com.example.crud_demo.repositories.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class PreLoad implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public void run(String... strings) throws Exception {
        String path = "most-popular-baby-names-2005-current-3.csv";
        List<User> users = getUsersFromCsv(path);
        List<Store> stores = getStoresFromCsv("prestadoras_servicos_telecomunicacoes.csv", 10);

        userRepository.saveAll(users);
        storeRepository.saveAll(stores);
    }

    public static List<User> getUsersFromCsv(String path) {
        Random random = new Random();
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileReader filereader = new FileReader(path);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            int i = 0;
            for (String[] row : allData) {
                if (i == 10)
                    break;
                String name = row[3];
                if(name == "N/I") name = "Kawaii is Justice";

                String email = name.toLowerCase() + "@gmail.com";
                int age = random.nextInt(100) % 13 + 17;
                users.add(new User(name, email, age));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public static List getStoresFromCsv(String path, int i) {
        Random random = new Random();
        ArrayList<Store> stores = new ArrayList<Store>();
        try {
            FileReader filereader = new FileReader(path);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .withCSVParser(
                            new CSVParserBuilder()
                                    .withSeparator(';')
                                    .build())
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                if (i == 0)
                    break;
                String name = row[4];
                String address = row[11];

                long number;
                try {
                    number = Long.parseLong(row[12]);
                } catch (NumberFormatException e) {
                    number = 679;
                }

                String phone = "9924-5" + number;
                long rating = random.nextInt(6);

                int age = random.nextInt(100) % 13 + 17;
                stores.add(new Store(name, address, number, phone, rating));
                i--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stores;
    }
}