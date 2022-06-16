/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.ninjaone.kafka.associate.service.avro.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class AssociateServiceRequestAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8926024030871207970L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AssociateServiceRequestAvroModel\",\"namespace\":\"com.ninjaone.kafka.associate.service.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"deviceId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"deviceServices\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"DeviceService\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"serviceTypeId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"typeDeviceId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}}]}}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"associateServiceStatus\",\"type\":{\"type\":\"enum\",\"name\":\"AssociateServiceStatus\",\"symbols\":[\"PENDING\",\"VALIDATED\",\"INVALID\"]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
  }

  private static final BinaryMessageEncoder<AssociateServiceRequestAvroModel> ENCODER =
      new BinaryMessageEncoder<AssociateServiceRequestAvroModel>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<AssociateServiceRequestAvroModel> DECODER =
      new BinaryMessageDecoder<AssociateServiceRequestAvroModel>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<AssociateServiceRequestAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<AssociateServiceRequestAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<AssociateServiceRequestAvroModel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<AssociateServiceRequestAvroModel>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this AssociateServiceRequestAvroModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a AssociateServiceRequestAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a AssociateServiceRequestAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static AssociateServiceRequestAvroModel fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String id;
  private java.lang.String sagaId;
  private java.lang.String deviceId;
  private java.util.List<com.ninjaone.kafka.associate.service.avro.model.DeviceService> deviceServices;
  private java.time.Instant createdAt;
  private com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus associateServiceStatus;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public AssociateServiceRequestAvroModel() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param sagaId The new value for sagaId
   * @param deviceId The new value for deviceId
   * @param deviceServices The new value for deviceServices
   * @param createdAt The new value for createdAt
   * @param associateServiceStatus The new value for associateServiceStatus
   */
  public AssociateServiceRequestAvroModel(java.lang.String id, java.lang.String sagaId, java.lang.String deviceId, java.util.List<com.ninjaone.kafka.associate.service.avro.model.DeviceService> deviceServices, java.time.Instant createdAt, com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus associateServiceStatus) {
    this.id = id;
    this.sagaId = sagaId;
    this.deviceId = deviceId;
    this.deviceServices = deviceServices;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
    this.associateServiceStatus = associateServiceStatus;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return sagaId;
    case 2: return deviceId;
    case 3: return deviceServices;
    case 4: return createdAt;
    case 5: return associateServiceStatus;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      null,
      null,
      null,
      new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
      null,
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = value$ != null ? value$.toString() : null; break;
    case 1: sagaId = value$ != null ? value$.toString() : null; break;
    case 2: deviceId = value$ != null ? value$.toString() : null; break;
    case 3: deviceServices = (java.util.List<com.ninjaone.kafka.associate.service.avro.model.DeviceService>)value$; break;
    case 4: createdAt = (java.time.Instant)value$; break;
    case 5: associateServiceStatus = (com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.String getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'sagaId' field.
   * @return The value of the 'sagaId' field.
   */
  public java.lang.String getSagaId() {
    return sagaId;
  }


  /**
   * Sets the value of the 'sagaId' field.
   * @param value the value to set.
   */
  public void setSagaId(java.lang.String value) {
    this.sagaId = value;
  }

  /**
   * Gets the value of the 'deviceId' field.
   * @return The value of the 'deviceId' field.
   */
  public java.lang.String getDeviceId() {
    return deviceId;
  }


  /**
   * Sets the value of the 'deviceId' field.
   * @param value the value to set.
   */
  public void setDeviceId(java.lang.String value) {
    this.deviceId = value;
  }

  /**
   * Gets the value of the 'deviceServices' field.
   * @return The value of the 'deviceServices' field.
   */
  public java.util.List<com.ninjaone.kafka.associate.service.avro.model.DeviceService> getDeviceServices() {
    return deviceServices;
  }


  /**
   * Sets the value of the 'deviceServices' field.
   * @param value the value to set.
   */
  public void setDeviceServices(java.util.List<com.ninjaone.kafka.associate.service.avro.model.DeviceService> value) {
    this.deviceServices = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public java.time.Instant getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.time.Instant value) {
    this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  /**
   * Gets the value of the 'associateServiceStatus' field.
   * @return The value of the 'associateServiceStatus' field.
   */
  public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus getAssociateServiceStatus() {
    return associateServiceStatus;
  }


  /**
   * Sets the value of the 'associateServiceStatus' field.
   * @param value the value to set.
   */
  public void setAssociateServiceStatus(com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus value) {
    this.associateServiceStatus = value;
  }

  /**
   * Creates a new AssociateServiceRequestAvroModel RecordBuilder.
   * @return A new AssociateServiceRequestAvroModel RecordBuilder
   */
  public static com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder newBuilder() {
    return new com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder();
  }

  /**
   * Creates a new AssociateServiceRequestAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new AssociateServiceRequestAvroModel RecordBuilder
   */
  public static com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder newBuilder(com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder other) {
    if (other == null) {
      return new com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder();
    } else {
      return new com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder(other);
    }
  }

  /**
   * Creates a new AssociateServiceRequestAvroModel RecordBuilder by copying an existing AssociateServiceRequestAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new AssociateServiceRequestAvroModel RecordBuilder
   */
  public static com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder newBuilder(com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel other) {
    if (other == null) {
      return new com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder();
    } else {
      return new com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder(other);
    }
  }

  /**
   * RecordBuilder for AssociateServiceRequestAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AssociateServiceRequestAvroModel>
    implements org.apache.avro.data.RecordBuilder<AssociateServiceRequestAvroModel> {

    private java.lang.String id;
    private java.lang.String sagaId;
    private java.lang.String deviceId;
    private java.util.List<com.ninjaone.kafka.associate.service.avro.model.DeviceService> deviceServices;
    private java.time.Instant createdAt;
    private com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus associateServiceStatus;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.deviceId)) {
        this.deviceId = data().deepCopy(fields()[2].schema(), other.deviceId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.deviceServices)) {
        this.deviceServices = data().deepCopy(fields()[3].schema(), other.deviceServices);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[4].schema(), other.createdAt);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.associateServiceStatus)) {
        this.associateServiceStatus = data().deepCopy(fields()[5].schema(), other.associateServiceStatus);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
    }

    /**
     * Creates a Builder by copying an existing AssociateServiceRequestAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.deviceId)) {
        this.deviceId = data().deepCopy(fields()[2].schema(), other.deviceId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.deviceServices)) {
        this.deviceServices = data().deepCopy(fields()[3].schema(), other.deviceServices);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[4].schema(), other.createdAt);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.associateServiceStatus)) {
        this.associateServiceStatus = data().deepCopy(fields()[5].schema(), other.associateServiceStatus);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.String getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder setId(java.lang.String value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'sagaId' field.
      * @return The value.
      */
    public java.lang.String getSagaId() {
      return sagaId;
    }


    /**
      * Sets the value of the 'sagaId' field.
      * @param value The value of 'sagaId'.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder setSagaId(java.lang.String value) {
      validate(fields()[1], value);
      this.sagaId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'sagaId' field has been set.
      * @return True if the 'sagaId' field has been set, false otherwise.
      */
    public boolean hasSagaId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'sagaId' field.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder clearSagaId() {
      sagaId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'deviceId' field.
      * @return The value.
      */
    public java.lang.String getDeviceId() {
      return deviceId;
    }


    /**
      * Sets the value of the 'deviceId' field.
      * @param value The value of 'deviceId'.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder setDeviceId(java.lang.String value) {
      validate(fields()[2], value);
      this.deviceId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'deviceId' field has been set.
      * @return True if the 'deviceId' field has been set, false otherwise.
      */
    public boolean hasDeviceId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'deviceId' field.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder clearDeviceId() {
      deviceId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'deviceServices' field.
      * @return The value.
      */
    public java.util.List<com.ninjaone.kafka.associate.service.avro.model.DeviceService> getDeviceServices() {
      return deviceServices;
    }


    /**
      * Sets the value of the 'deviceServices' field.
      * @param value The value of 'deviceServices'.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder setDeviceServices(java.util.List<com.ninjaone.kafka.associate.service.avro.model.DeviceService> value) {
      validate(fields()[3], value);
      this.deviceServices = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'deviceServices' field has been set.
      * @return True if the 'deviceServices' field has been set, false otherwise.
      */
    public boolean hasDeviceServices() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'deviceServices' field.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder clearDeviceServices() {
      deviceServices = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * @return The value.
      */
    public java.time.Instant getCreatedAt() {
      return createdAt;
    }


    /**
      * Sets the value of the 'createdAt' field.
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder setCreatedAt(java.time.Instant value) {
      validate(fields()[4], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder clearCreatedAt() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'associateServiceStatus' field.
      * @return The value.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus getAssociateServiceStatus() {
      return associateServiceStatus;
    }


    /**
      * Sets the value of the 'associateServiceStatus' field.
      * @param value The value of 'associateServiceStatus'.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder setAssociateServiceStatus(com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus value) {
      validate(fields()[5], value);
      this.associateServiceStatus = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'associateServiceStatus' field has been set.
      * @return True if the 'associateServiceStatus' field has been set, false otherwise.
      */
    public boolean hasAssociateServiceStatus() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'associateServiceStatus' field.
      * @return This builder.
      */
    public com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel.Builder clearAssociateServiceStatus() {
      associateServiceStatus = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AssociateServiceRequestAvroModel build() {
      try {
        AssociateServiceRequestAvroModel record = new AssociateServiceRequestAvroModel();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
        record.sagaId = fieldSetFlags()[1] ? this.sagaId : (java.lang.String) defaultValue(fields()[1]);
        record.deviceId = fieldSetFlags()[2] ? this.deviceId : (java.lang.String) defaultValue(fields()[2]);
        record.deviceServices = fieldSetFlags()[3] ? this.deviceServices : (java.util.List<com.ninjaone.kafka.associate.service.avro.model.DeviceService>) defaultValue(fields()[3]);
        record.createdAt = fieldSetFlags()[4] ? this.createdAt : (java.time.Instant) defaultValue(fields()[4]);
        record.associateServiceStatus = fieldSetFlags()[5] ? this.associateServiceStatus : (com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus) defaultValue(fields()[5]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<AssociateServiceRequestAvroModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<AssociateServiceRequestAvroModel>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<AssociateServiceRequestAvroModel>
    READER$ = (org.apache.avro.io.DatumReader<AssociateServiceRequestAvroModel>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}









