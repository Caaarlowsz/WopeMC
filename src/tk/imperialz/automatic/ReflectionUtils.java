package tk.imperialz.automatic;

import java.lang.reflect.Field;

public class ReflectionUtils {
	public static void setValue(final String field, final Class<?> clazz, final Object instance, final Object value) {
		try {
			final Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			f.set(instance, value);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static Object getValue(final String field, final Class<?> clazz, final Object instance) {
		try {
			final Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			return f.get(instance);
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	public static void setValue(final String field, final Object instance, final Object value) {
		try {
			final Field f = instance.getClass().getDeclaredField(field);
			f.setAccessible(true);
			f.set(instance, value);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static Object getValue(final String field, final Object instance) {
		try {
			final Field f = instance.getClass().getDeclaredField(field);
			f.setAccessible(true);
			return f.get(instance);
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}
}
