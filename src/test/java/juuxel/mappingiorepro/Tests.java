package juuxel.mappingiorepro;

import net.fabricmc.mappingio.adapter.MappingSourceNsSwitch;
import net.fabricmc.mappingio.format.Tiny2Reader;
import net.fabricmc.mappingio.format.Tiny2Writer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

class Tests {
    @Test
    void passThrough() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (InputStream in = Tests.class.getResourceAsStream("/mappings.tiny");
             InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
             OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
             Tiny2Writer tinyWriter = new Tiny2Writer(writer, false)) {
            Tiny2Reader.read(reader, new CheckingWriter(tinyWriter));
        }
    }

    @Test
    void switchNs() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (InputStream in = Tests.class.getResourceAsStream("/mappings.tiny");
             InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
             OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
             Tiny2Writer tinyWriter = new Tiny2Writer(writer, false)) {
            Tiny2Reader.read(reader, new MappingSourceNsSwitch(new CheckingWriter(tinyWriter), "intermediary"));
        }
    }
}
