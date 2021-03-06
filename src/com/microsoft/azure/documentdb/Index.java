package com.microsoft.azure.documentdb;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONObject;

public abstract class Index extends JsonSerializable {
    /**
     * Creates a new instance of the Index class.
     *
     * @param indexKind the index kind {@link IndexKind}
     */
    protected Index(IndexKind indexKind) {
        super();
        this.setKind(indexKind);
    }

    /**
     * Creates a new instance of the Index class.
     *
     * @param jsonString the json string that represents the index.
     * @param indexKind the index kind {@link IndexKind}
     */
    protected Index(String jsonString, IndexKind indexKind) {
        super(jsonString);
        this.setKind(indexKind);
    }

    /**
     * Creates a new instance of the Index class.
     *
     * @param jsonObject the json object that represents the index.
     * @param indexKind the index kind {@link IndexKind}
     */
    protected Index(JSONObject jsonObject, IndexKind indexKind) {
        super(jsonObject);
        this.setKind(indexKind);
    }

    /**
     * Gets index kind.
     * 
     * @return the index kind.
     */
    public IndexKind getKind() {
        IndexKind result = null;
        try {
            result = IndexKind.valueOf(WordUtils.capitalize(super.getString(Constants.Properties.INDEX_KIND)));
        } catch(IllegalArgumentException e) {
            this.getLogger().warning(
                    String.format("Invalid index kind value %s.", super.getString(Constants.Properties.INDEX_KIND)));
        }
        
        return result;
    }

    /**
     * Sets index kind.
     * 
     * @param indexKind the index kind.
     */
    private void setKind(IndexKind indexKind) {
        super.set(Constants.Properties.INDEX_KIND, indexKind.name());
    }

    /**
     * Returns an instance of RangeIndex class with specified DataType.
     *
     * Here is an example to create RangeIndex instance passing in the DataType:
     * 
     * <pre>
     * {@code
     * 
     * RangeIndex rangeIndex = Index.Range(DataType.Number);
     * 
     * }
     * </pre>
     * 
     * @param dataType the data type.
     * @return an instance of RangeIndex type.
     */
    public static RangeIndex Range(DataType dataType) {
        return new RangeIndex(dataType);
    }

    /**
     * Returns an instance of RangeIndex class with specified DataType and precision.
     *
     * Here is an example to create RangeIndex instance passing in the DataType and precision:
     * 
     * <pre>
     * {@code
     * 
     * RangeIndex rangeIndex = Index.Range(DataType.Number, -1);
     * 
     * }
     * </pre>
     *
     * @param dataType specifies the target data type for the index path specification.
     * @param precision specifies the precision to be used for the data type associated with this index.
     * @return an instance of RangeIndex type.
     */
    public static RangeIndex Range(DataType dataType, int precision) {
        return new RangeIndex(dataType, precision);
    }

    /**
     * Returns an instance of HashIndex class with specified DataType.
     *
     * Here is an example to create HashIndex instance passing in the DataType:
     * 
     * <pre>
     * {@code
     * 
     * HashIndex hashIndex = Index.Hash(DataType.String);
     * } 
     * </pre>
     * 
     * @param dataType specifies the target data type for the index path specification.
     * @return an instance of HashIndex type.
     */
    public static HashIndex Hash(DataType dataType) {
        return new HashIndex(dataType);
    }

    /**
     * Returns an instance of HashIndex class with specified DataType and precision.
     *
     * Here is an example to create HashIndex instance passing in the DataType and precision:
     *
     * HashIndex hashIndex = Index.Hash(DataType.String, 3);
     *
     * @param dataType specifies the target data type for the index path specification.
     * @param precision specifies the precision to be used for the data type associated with this index.
     * @return an instance of HashIndex type.
     */
    public static HashIndex Hash(DataType dataType, int precision) {
        return new HashIndex(dataType, precision);
    }

    /**
     * Returns an instance of SpatialIndex class with specified DataType.
     *
     * Here is an example to create SpatialIndex instance passing in the DataType:
     *
     * SpatialIndex spatialIndex = Index.Spatial(DataType.Point);
     *
     * @param dataType specifies the target data type for the index path specification.
     * @return an instance of SpatialIndex type.
     */
    public static SpatialIndex Spatial(DataType dataType) {
        return new SpatialIndex(dataType);
    }
}
