package ua.lviv.iot.loomshop.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.lviv.iot.loomshop.dao.LoomDAO;
import ua.lviv.iot.loomshop.models.loom.Loom;
import ua.lviv.iot.loomshop.services.LoomService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoomServiceImpl implements LoomService {

    private final LoomDAO loomDAO;

    /**
     * Returns 200 status code and created Loom object
     */
    @Override
    public ResponseEntity<Loom> createLoom(Loom loom) {
        loomDAO.save(loom);
        return new ResponseEntity<>(loom, HttpStatus.OK);
    }

    /**
     * Returns 200 status code and List of Loom objects if List of looms has at least 1 loom object
     * Returns 404 status code if List of looms is empty
     */
    @Override
    public ResponseEntity<List<Loom>> getAllLooms() {
        List<Loom> looms = loomDAO.findAll();

        if (!looms.isEmpty()) {
            return new ResponseEntity<>(looms, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns 200 status code and Loom object if loom object with this id exists
     * Returns 404 status code if loom with this id is missing
     */
    @Override
    public ResponseEntity<Loom> getLoom(Long id) {
        Loom loom = loomDAO.findLoomById(id);

        if (loom != null) {
            return new ResponseEntity<>(loom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns 200 status code and previous loom object if loom with this id exists
     * Returns 404 status code if loom with this id is missing
     */
    @Override
    public ResponseEntity<Loom> updateLoomById(Long id, Loom newLoom) {
        Loom loomOptional = loomDAO.findLoomById(id);

        // Save locally old loom with copy constructor
        Loom oldLoom = loomOptional != null ? new Loom(loomDAO.findLoomById(id)) : null;

        newLoom.setId(id);

        if (loomOptional != null) {
            loomDAO.save(newLoom);
            return new ResponseEntity<>(oldLoom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns 200 status code if List of looms has at least 1 loom object
     * Returns 404 status code if List of looms is empty
     */
    @Override
    public ResponseEntity<Loom> deleteAllLooms() {
        List<Loom> looms = loomDAO.findAll();

        if (!looms.isEmpty()) {
            loomDAO.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns 200 status code if loom object with this id exists
     * Returns 404 status code if loom with this id is missing
     */
    @Override
    public ResponseEntity<Loom> deleteLoomById(Long id) {
        Loom loom = loomDAO.findLoomById(id);

        if (loom != null) {
            loomDAO.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
