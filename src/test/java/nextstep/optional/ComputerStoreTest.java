package nextstep.optional;

import nextstep.optional.Computer.Soundcard;
import nextstep.optional.Computer.USB;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ComputerStoreTest {
    private static final String UNKNOWN_VERSION = "UNKNOWN";

    @Test
    public void getVersion() {
        String version = "pobi's usb";
        Soundcard soundcard = new Soundcard(new USB(version));
        Computer computer = new Computer(soundcard);
        assertThat(ComputerStore.getVersion(computer)).isEqualTo(version);
    }

    @Test
    public void getVersionWhenComputerIsNull() throws Exception {
        assertThat(ComputerStore.getVersion(null)).isEqualTo(UNKNOWN_VERSION);
    }

    @Test
    public void getVersionWhenSoundcardIsNull() throws Exception {
        Computer computer = new Computer(null);
        assertThat(ComputerStore.getVersion(computer)).isEqualTo(UNKNOWN_VERSION);
    }

    @Test
    public void getVersionWhenUSBIsNull() throws Exception {
        Computer computer = new Computer(new Soundcard(null));
        assertThat(ComputerStore.getVersion(computer)).isEqualTo(UNKNOWN_VERSION);
    }

    @Test
    public void getVersionOptional() {
        String version = "pobi's usb";
        Soundcard soundcard = new Soundcard(new USB(version));
        Computer computer = new Computer(soundcard);
        assertThat(ComputerStore.getVersionOptional(computer)).isEqualTo(version);
    }

    @Test
    public void getVersionOptionalWhenComputerIsNull() throws Exception {
        assertThat(ComputerStore.getVersionOptional(null)).isEqualTo(UNKNOWN_VERSION);
    }

    @Test
    public void getVersionOptionalWhenSoundcardIsNull() throws Exception {
        Computer computer = new Computer(null);
        assertThat(ComputerStore.getVersionOptional(computer)).isEqualTo(UNKNOWN_VERSION);
    }

    @Test
    public void getVersionOptionalWhenUSBIsNull() throws Exception {
        Computer computer = new Computer(new Soundcard(null));
        assertThat(ComputerStore.getVersionOptional(computer)).isEqualTo(UNKNOWN_VERSION);
    }
}
