package wasm.instruction2.memory;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.instruction2.dump.DumpMemory;
import wasm.core2.model.Dump;
import wasm.core2.model.index.MemoryIndex;
import wasm.core2.numeric.U32;
import wasm.core2.numeric.U64;

public class I64Load implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpMemory(reader.readLeb128U32(), reader.readLeb128U32());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;

        DumpMemory a = (DumpMemory) args;

        // System.err.println("So, which memory ?");
        byte[] bytes = mi.readBytes(new MemoryIndex(new U32(0)), a, 8);

        mi.pushU64(new U64(new byte[] {
            bytes[7], bytes[6], bytes[5], bytes[4], bytes[3], bytes[2], bytes[1], bytes[0]
        }));
    }

}
