export type PurchaseType =
  | 'PHYSICAL_CD'
  | 'PHYSICAL_DVD'
  | 'DIGITAL_DOWNLOAD'
  | 'STREAMING_ACCESS';

export type ShippingAddress = {
  line1: string;
  city: string;
  pincode: string;
};

export type CreateOrderRequest = {
  userId: string;
  movieId: string;
  purchaseType: PurchaseType;
  shippingAddress?: ShippingAddress;
};
