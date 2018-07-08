package tech.blur.eventhub.repositories;

import tech.blur.eventhub.models.Tag;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Реализиция, хранящая все данные в памяти приложения
 */
@Repository
public class InMemoryTagRepository implements TagRepository {

    private Map<String, Tag> tagCache = new HashMap<>();

    public InMemoryTagRepository() {
        tagCache.put("1", new Tag("1", "mathematics", 0));
        tagCache.put("2", new Tag("2", "javascript", 0));
    }


    @Override
    public Tag fetchTag(final String id) {
        return tagCache.get(id);
    }

    @Override
    public Tag updateTag(final Tag tag) {
        tagCache.put(tag.getId(), tag);
        return tag;
    }

    @Override
    public void deleteTag(final String id) {
        tagCache.remove(id);
    }

    @Override
    public Tag createTag(final Tag tag) {
        tag.setId(String.valueOf(System.currentTimeMillis()));  //очень плохой способ генерировать Id, тут только для примера
        tagCache.put(tag.getId(), tag);
        return tag;
    }

    @Override
    public Collection<Tag> getAllTags() {
        return tagCache.values();
    }
}