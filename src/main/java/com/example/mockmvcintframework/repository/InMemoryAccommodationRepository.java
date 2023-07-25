package com.example.mockmvcintframework.repository;

import com.example.mockmvcintframework.domain.Accommodation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAccommodationRepository implements AccommodationRepository {
    private final HashMap<Long, Accommodation> entities = new HashMap<>();

    @Override
    public Optional<Accommodation> create(Accommodation entity) {
        Accommodation entityToSave = Accommodation.builder()
                .id(entities.size() + 1L)
                .addressId(entity.getAddressId())
                .personId(entity.getPersonId())
                .accommodationDate(entity.getAccommodationDate())
                .build();

        return Optional.of(entityToSave).map(createdAccommodation -> {
            entities.put(createdAccommodation.getId(), createdAccommodation);

            return createdAccommodation;
        });
    }

    @Override
    public Optional<Accommodation> getById(Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Optional<List<Accommodation>> findAll() {
        return Optional.of(new ArrayList<>(entities.values()));
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return getById(id).map(foundAccommodation -> {
            accommodation.setId(id);
            entities.replace(id, accommodation);

            return entities.get(id);
        });
    }

    @Override
    public void delete(Long id) {
        entities.remove(id);
    }
}
