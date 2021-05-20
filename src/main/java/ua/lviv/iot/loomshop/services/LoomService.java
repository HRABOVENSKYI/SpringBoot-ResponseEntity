package ua.lviv.iot.loomshop.services;

import org.springframework.http.ResponseEntity;
import ua.lviv.iot.loomshop.models.loom.Loom;

import java.util.List;

public interface LoomService {

    ResponseEntity<Loom> createLoom(Loom loom);

    ResponseEntity<List<Loom>> getAllLooms();

    ResponseEntity<Loom> getLoom(Long id);

    ResponseEntity<Loom> updateLoomById(Long id, Loom newLoom);

    ResponseEntity<Loom> deleteAllLooms();

    ResponseEntity<Loom> deleteLoomById(Long id);
}
