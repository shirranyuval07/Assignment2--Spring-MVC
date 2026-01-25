package taskmanager.dao;

import org.springframework.stereotype.Repository;
import taskmanager.entity.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightDao {
    private static final String FILE_NAME = "flights.dat";

    public void saveAll(List<Flight> flights) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(flights);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Flight> loadAll() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Flight>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}