package rsm.third_task.enums;

import java.util.stream.Stream;

public enum NotAllowedFileExtensions {
    cmd(".cmd"),
    com(".com"),
    dll(".dll"),
    dmg(".dmg"),
    exe(".exe"),
    iso(".iso"),
    jar(".jar"),
    js(".js");

    NotAllowedFileExtensions(String notAllowedExtension) {
    }

    public static Stream<NotAllowedFileExtensions> stream() {
        return Stream.of(NotAllowedFileExtensions.values());
    }
}
