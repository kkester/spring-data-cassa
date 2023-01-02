import React from 'react';
import './css/App.css';
import { Link, Route, Routes } from 'react-router-dom';
import { Main } from './pages/Main';
import { Monopoly } from './pages/Monopoly';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Link to="/">New Game</Link>
      </header>
      <hr/>
      <main className="App-main">
        <Routes>
          <Route path="/" element={ <Main/> }/>
          <Route path="/monopoly/:gameId" element={ <Monopoly/> } />
        </Routes>
      </main>
    </div>
  );
}

export default App;
