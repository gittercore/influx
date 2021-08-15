package wtf.hippo.influx;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ArrayList;

public class DataBase {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection collection;
    public DataBase() {
        Dotenv dotenv = Dotenv.load();
        mongoClient = MongoClients.create(dotenv.get("URI"));
        database = mongoClient.getDatabase(dotenv.get("DB"));
        collection = database.getCollection(dotenv.get("COLLECTION"));

    }
    public void addToDB(Document document) {
        collection.insertOne(document);
    }
    public List<Document> find(Document doc) {
        List<Document> result = new ArrayList<Document>();
        collection.find(doc).into(result);

        return result;
    }
    public void modify(Bson query, Document update) {
        collection.findOneAndReplace(query, update);
    }
}