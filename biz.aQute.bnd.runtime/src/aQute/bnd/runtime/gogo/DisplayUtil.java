package aQute.bnd.runtime.gogo;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;

public class DisplayUtil {

	static String objectClass(Map<String, Object> map) {
		String[] object = (String[]) map.get(Constants.OBJECTCLASS);

		return objectClass(object);
	}

	public static String objectClass(String[] object) {
		return Stream.of(object)
			.map(DisplayUtil::shorten)
			.collect(Collectors.joining("\n"));
	}

	static String shorten(String className) {
		String[] split = className.split("\\.");
		StringBuilder sb = new StringBuilder();
		sb.append(split[split.length - 1]);
		return sb.toString();

	}

	public static String dateTime(long time) {
		if (time == 0)
			return "0";
		else
			return Instant.ofEpochMilli(time)
				.toString();
	}

	public static String lastModified(long time) {
		if (time == 0)
			return "?";

		Instant now = Instant.now();
		Instant modified = Instant.ofEpochMilli(time);
		Duration d = Duration.between(modified, now);
		long millis = d.toMillis();
		if (millis < 300_000L) {
			return (millis + 500L) / 1000L + " secs ago";
		}
		if (millis < 60L * 300_000L) {
			return (millis + 500L) / 60_000L + " mins ago";
		}
		if (millis < 60L * 60L * 300_000L) {
			return (millis + 500L) / (60L * 60_000L) + " hrs ago";
		}
		if (millis < 24L * 60L * 60L * 300_000L) {
			return (millis + 500L) / (24L * 60L * 60_000L) + " days ago";
		}
		return dateTime(time);
	}

	public static Map<String, Object> toMap(ServiceReference<?> ref) {
		Map<String, Object> map = new HashMap<>();
		for (String key : ref.getPropertyKeys()) {
			map.put(key, ref.getProperty(key));
		}
		return map;
	}

}
