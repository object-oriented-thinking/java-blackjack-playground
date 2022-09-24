package nextstep.optional;

import nextstep.optional.Computer.Soundcard;
import nextstep.optional.Computer.USB;

import java.util.Optional;

public class ComputerStore {
    private static final String UNKNOWN_VERSION = "UNKNOWN";
    private static final USB UNKNOWN_USB = new USB(UNKNOWN_VERSION);
    private static final Soundcard UNKNOWN_SOUNDCARD = new Soundcard(UNKNOWN_USB);
    private static final Computer UNKNOWN_COMPUTER = new Computer(UNKNOWN_SOUNDCARD);

    public static String getVersion(Computer computer) {
        String version = UNKNOWN_VERSION;
        if (computer != null) {
            Soundcard soundcard = computer.getSoundcard();
            if (soundcard != null) {
                USB usb = soundcard.getUsb();
                if (usb != null) {
                    version = usb.getVersion();
                }
            }
        }
        return version;
    }

    public static String getVersionOptional(Computer computer) {
        return Optional.ofNullable(computer).orElse(UNKNOWN_COMPUTER)
            .getOptionalSoundcard().orElse(UNKNOWN_SOUNDCARD)
            .getOptionalUsb().orElse(UNKNOWN_USB)
            .getVersion();
    }
}
