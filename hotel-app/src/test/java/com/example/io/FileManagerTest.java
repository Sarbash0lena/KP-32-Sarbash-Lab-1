package com.example.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.model.Room;

class FileManagerTest {

    private final FileManager fileManager = new FileManager();
    private final String testFile = "test_rooms.json";

    @Test
    void exportAndImportRooms_shouldWorkCorrectly() throws IOException {
        List<Room> original = List.of(
                new Room(101, 300.0),
                new Room(102, 250.0)
        );

        fileManager.exportRooms(original, testFile);
        List<Room> imported = fileManager.importRooms(testFile);

        assertEquals(original.size(), imported.size());
        assertEquals(original.get(0).getNumber(), imported.get(0).getNumber());
        assertEquals(original.get(1).getPricePerNight(), imported.get(1).getPricePerNight());
    }

    @AfterEach
    void cleanUp() {
        new File(testFile).delete();
    }
}
