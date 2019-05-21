package by.vsu.bramberry.updatechecker.model.service;

import by.vsu.bramberry.updatechecker.model.entity.CustomSequences;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@AllArgsConstructor
public class SequenceGeneratorService {
    private MongoOperations mongo;

    public Long getNextSequence(String seqName) {
        CustomSequences counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                CustomSequences.class);
        return Objects.requireNonNull(counter).getSeq();
    }
}
