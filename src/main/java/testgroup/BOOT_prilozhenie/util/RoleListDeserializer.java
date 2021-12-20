package testgroup.BOOT_prilozhenie.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import testgroup.BOOT_prilozhenie.model.Role;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RoleListDeserializer extends StdDeserializer<List<Role>> {
    public RoleListDeserializer() {
        this(null);
    }

    public RoleListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<Role> deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
        var result = new ArrayList<Role>();
        var token = jsonparser.nextToken();

        while (!token.equals(JsonToken.END_ARRAY)) {
            if (token.equals(JsonToken.VALUE_STRING)) {
                result.add(new Role(jsonparser.getText()));
            }
            token = jsonparser.nextToken();
        }

        return result;
    }
}