package net.glouz.myapp.model.models.product;


import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class ProductDetailsContract
    implements BaseColumns {
  public static final String AUTHORITY = "net.glouz.myapp.model.models.product";

  public static final String CONTENT_URI_PATH = "productdetails";

  public static final String MIMETYPE_TYPE = "productdetails";
  public static final String MIMETYPE_NAME = "net.glouz.myapp.model.models.product.provider";

  public static final int CONTENT_URI_PATTERN_MANY = 7;
  public static final int CONTENT_URI_PATTERN_ONE = 8;

  public static final Uri CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).appendPath(CONTENT_URI_PATH).build();

  private ProductDetailsContract() {
  }


  public static final String INSTOCK = "inStock";
  public static final String PRICETYPE = "priceType";
  public static final String SKU = "sku";
  public static final String ADDITIONALINFO = "additionalInfo";
  public static final String CAREINFO = "careInfo";
  public static final String DESCRIPTION = "description";
  public static final String PRODUCT = "product";
}
