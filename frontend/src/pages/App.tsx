import { OrderForm } from '../components/OrderForm';

const posters = [
  { title: 'Dune: Part Two', year: '2024' },
  { title: 'Oppenheimer', year: '2023' },
  { title: 'Interstellar', year: '2014' },
  { title: 'The Batman', year: '2022' },
  { title: 'Blade Runner 2049', year: '2017' }
];

export function App() {
  return (
    <main className="app-shell">
      <header className="topbar">
        <h1>CinemaPeak</h1>
        <button className="btn btn-primary" data-testid="login-cta">Login</button>
      </header>

      <section className="hero" data-testid="hero-banner">
        <div>
          <p className="caption">Now Streaming</p>
          <h2>The Future of Cinema, Curated</h2>
          <p className="muted">Browse titles, choose your purchase mode, and checkout in seconds.</p>
        </div>
      </section>

      <section className="row-section" data-testid="catalog-row">
        <div className="row-header">
          <h3>Continue Watching</h3>
        </div>
        <div className="poster-row">
          {posters.map((poster) => (
            <article className="poster-card" key={poster.title} data-testid="poster-card">
              <div className="poster-art" />
              <div className="poster-meta">
                <strong>{poster.title}</strong>
                <span>{poster.year}</span>
              </div>
            </article>
          ))}
        </div>
      </section>

      <section className="checkout-section" data-testid="checkout-section">
        <h3>Checkout</h3>
        <OrderForm />
      </section>
    </main>
  );
}
