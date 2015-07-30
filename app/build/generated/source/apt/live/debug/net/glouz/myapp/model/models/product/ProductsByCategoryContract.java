package net.glouz.myapp.model.models.product;


import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class ProductsByCategoryContract
    implements BaseColumns {
  public static final String AUTHORITY = "net.glouz.myapp.model.models.product";

  public static final String CONTENT_URI_PATH = "productsbycategory";

  public static final String MIMETYPE_TYPE = "productsbycategory";
  public static final String MIMETYPE_NAME = "net.glouz.myapp.model.models.product.provider";

  public static final int CONTENT_URI_PATTERN_MANY = 9;
  public static final int CONTENT_URI_PATTERN_ONE = 10;

  public static final Uri CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).appendPath(CONTENT_URI_PATH).build();

  private ProductsByCategoryContract() {
  }


  public static final String DESCRIPTION = "description";
  public static final String ITEMCOUNT = "itemCount";
  public static final String REDIRECTURL = "redirectUrl";
  public static final String SORTTYPE = "sortType";
  public static final String PRODUCTCATEGORY = "productCategory";
}
