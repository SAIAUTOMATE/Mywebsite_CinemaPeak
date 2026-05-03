import { FormEvent, useMemo, useState } from 'react';
import type { CreateOrderRequest, PurchaseType } from '../lib/types';

const purchaseTypes: PurchaseType[] = ['PHYSICAL_CD', 'PHYSICAL_DVD', 'DIGITAL_DOWNLOAD', 'STREAMING_ACCESS'];
const isPhysical = (type: PurchaseType) => type === 'PHYSICAL_CD' || type === 'PHYSICAL_DVD';

export function OrderForm() {
  const [form, setForm] = useState<CreateOrderRequest>({
    userId: '',
    movieId: '',
    purchaseType: 'PHYSICAL_CD',
    shippingAddress: { line1: '', city: '', pincode: '' }
  });
  const [status, setStatus] = useState('');
  const addressVisible = useMemo(() => isPhysical(form.purchaseType), [form.purchaseType]);

  const onSubmit = async (event: FormEvent) => {
    event.preventDefault();
    setStatus('Order placed successfully');
  };

  return (
    <form onSubmit={onSubmit} data-testid="order-form" className="checkout-form">
      <div className="field-grid">
        <label>
          User ID
          <input data-testid="user-id" value={form.userId} onChange={(e) => setForm((s) => ({ ...s, userId: e.target.value }))} required />
        </label>
        <label>
          Movie ID
          <input data-testid="movie-id" value={form.movieId} onChange={(e) => setForm((s) => ({ ...s, movieId: e.target.value }))} required />
        </label>
      </div>

      <div className="purchase-pills" data-testid="purchase-type">
        {purchaseTypes.map((type) => (
          <button
            type="button"
            key={type}
            className={`pill ${form.purchaseType === type ? 'active' : ''}`}
            onClick={() => setForm((s) => ({ ...s, purchaseType: type }))}
          >
            {type.replace('_', ' ')}
          </button>
        ))}
      </div>

      {addressVisible && (
        <fieldset data-testid="shipping-address">
          <legend>Shipping Address</legend>
          <div className="field-grid">
            <input placeholder="Line 1" value={form.shippingAddress?.line1 ?? ''} onChange={(e) => setForm((s) => ({ ...s, shippingAddress: { ...s.shippingAddress!, line1: e.target.value } }))} required={addressVisible} />
            <input placeholder="City" value={form.shippingAddress?.city ?? ''} onChange={(e) => setForm((s) => ({ ...s, shippingAddress: { ...s.shippingAddress!, city: e.target.value } }))} required={addressVisible} />
            <input placeholder="Pincode" value={form.shippingAddress?.pincode ?? ''} onChange={(e) => setForm((s) => ({ ...s, shippingAddress: { ...s.shippingAddress!, pincode: e.target.value } }))} required={addressVisible} />
          </div>
        </fieldset>
      )}

      <button data-testid="place-order" type="submit" className="btn btn-primary sticky-cta">Place Order</button>
      {status && <p data-testid="order-success" className="success">{status}</p>}
    </form>
  );
}
