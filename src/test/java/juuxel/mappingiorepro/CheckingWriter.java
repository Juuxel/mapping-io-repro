package juuxel.mappingiorepro;

import net.fabricmc.mappingio.MappingWriter;
import net.fabricmc.mappingio.adapter.ForwardingMappingVisitor;

import java.io.IOException;

public class CheckingWriter extends ForwardingMappingVisitor implements MappingWriter {
    private final MappingWriter next;

    public CheckingWriter(MappingWriter next) {
        super(next);
        this.next = next;
    }

    @Override
    public boolean visitField(String srcName, String srcDesc) throws IOException {
        assert srcName.charAt(0) != '\n' : "'" + srcName.replace("\n", "\\n") + "' has been corrupted";
        return super.visitField(srcName, srcDesc);
    }

    @Override
    public void close() throws IOException {
        next.close();
    }
}
