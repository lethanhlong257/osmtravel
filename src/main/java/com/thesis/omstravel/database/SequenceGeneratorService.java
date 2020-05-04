package com.thesis.omstravel.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class SequenceGeneratorService implements SequenceGenerator {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public long generateSequence(String seqName) {
        Query query = new Query(Criteria.where("_id").is(seqName));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true).upsert(true);

        DatabaseSequence databaseSequence = mongoOperations.findAndModify(query, update, options, DatabaseSequence.class);

        return !Objects.isNull(databaseSequence) ? databaseSequence.getSeq() : 1;

    }
}

