import React from 'react';
import './css/App.css';
import { Route, Routes } from 'react-router-dom';
import { Main } from './pages/Main';
import { Monopoly } from './pages/Monopoly';

function App() {
  return (
    <div className="App">
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
