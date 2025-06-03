package com.example.io;

import com.example.model.Room;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileManager {

    private final ObjectMapper mapper = new ObjectMapper();

    public void exportRooms(List<Room> rooms, String filePath) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), rooms);
    }

    public List<Room> importRooms(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), new TypeReference<List<Room>>() {
        });
    }
}
