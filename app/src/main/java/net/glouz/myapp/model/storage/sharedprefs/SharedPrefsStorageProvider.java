package net.glouz.myapp.model.storage.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import net.glouz.myapp.SampleApplication;


/**
 * Utilitity class for saving and retrieving data in the shared prefs.
 * 
 * @author glouzonf
 * 
 */
public class SharedPrefsStorageProvider {

	public static final String TAG = SharedPrefsStorageProvider.class.getSimpleName();
	
//	/** example of String shared prefs **/
//	public static final String DEVICE_ID_ENV_KEY = "device_id_switch";
//	/** example of int shared prefs **/
//	public static final int FIRST_TIME_STATE_UNUSED = 0;

	/** String for wishlist **/
	public static final String PRODUCT_WISHLIST_KEY = "product_wishlist_key";
	/** String for bag **/
	public static final String PRODUCT_BAG_KEY = "product_bag_key";
	/** int for productId **/
	public static final String ID_PRODUCT_KEY = "id_product_key";

	public static final String PREFS_NAME = "sampleApp";

	/**
	 * Returns the shared preferences.
	 * 
	 * @return Shared prefs.
	 */
	public static SharedPreferences getPreferences() {

		return SampleApplication.getInstance().getSharedPreferences(PREFS_NAME,
				Context.MODE_MULTI_PROCESS);

	}

	/**
	 * Saves the preference.
	 * 
	 * @param key
	 *            Key for preference.
	 * @param value
	 *            Value for preference store.
	 */
	public static void savePreferences(String key, String value) {

		SharedPreferences sharedPreferences = getPreferences();
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();

	}

	/**
	 * Saves the preference.
	 * 
	 * @param key
	 *            Key for preference.
	 * @param value
	 *            Value for preference store.
	 */
	public static void savePreferences(String key, Boolean value) {

		SharedPreferences sharedPreferences = getPreferences();
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();

	}

	/**
	 * Saves the preference.
	 * 
	 * @param key
	 *            Key for preference.
	 * @param value
	 *            Value for preference store.
	 */
	public static void savePreferences(String key, int value) {

		SharedPreferences sharedPreferences = getPreferences();
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();

	}

	/**
	 * Saves the preference.
	 * 
	 * @param key
	 *            Key for preference.
	 * @param value
	 *            Value for preference store.
	 */
	public static void savePreferences(String key, long value) {

		SharedPreferences sharedPreferences = getPreferences();
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putLong(key, value);
		editor.commit();

	}

	/**
	 * retrieve a string saved preference.
	 * 
	 * @param key
	 * @return
	 */
	public static String getPreferencesString(String key) {
		return getPreferences().getString(key, null);
	}

	/**
	 * retrieve an int saved preference.
	 * 
	 * @param key
	 * @return
	 */
	public static int getPreferencesInt(String key) {
		return getPreferences().getInt(key, 0);
	}

	/**
	 * retrieve a boolean saved preference.
	 * 
	 * @param key
	 * @return
	 */
	public static boolean getPreferencesBoolean(String key) {
		return getPreferences().getBoolean(key, false);
	}

	/**
	 * retrieve a long saved preference.
	 * 
	 * @param key
	 * @return
	 */
	public static long getPreferencesLong(String key) {
		long value = 0;

		try {
			value = getPreferences().getLong(key, 0);
		} catch (ClassCastException e) {
			value = 0;
		}

		return value;
	}

}
