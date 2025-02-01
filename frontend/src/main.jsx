import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import Items from './components/item.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Items />
  </StrictMode>,
)
