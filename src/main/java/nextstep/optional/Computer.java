package nextstep.optional;

import java.util.Optional;

public class Computer {
    private final Soundcard soundcard;

    public Computer(Soundcard soundcard) {
        this.soundcard = soundcard;
    }

    public Soundcard getSoundcard() {
        return soundcard;
    }

    public Optional<Soundcard> getOptionalSoundcard() {
        return Optional.ofNullable(soundcard);
    }

    public static class Soundcard {
        private final USB usb;

        public Soundcard(USB usb) {
            super();
            this.usb = usb;
        }

        public USB getUsb() {
            return usb;
        }

        public Optional<USB> getOptionalUsb() {
            return Optional.ofNullable(usb);
        }
    }

    public static class USB {
        private final String version;

        public USB(String version) {
            super();
            this.version = version;
        }

        public String getVersion() {
            return this.version;
        }
    }
}
