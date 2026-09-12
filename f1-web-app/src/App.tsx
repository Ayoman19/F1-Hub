import React from 'react';
import F1News from './components/F1News';
import RaceSchedule from './components/RaceSchedule';
import Standings from './components/Standings';
import './assets/styles.css';

const App: React.FC = () => {
  return (
    <div className="app">
      <header>
        <h1>🏎️ Formula 1 Dashboard</h1>
      </header>
      
      <div className="app-content">
        <F1News />
        <RaceSchedule />
        <Standings />
      </div>
      
      <footer></footer>
    </div>
  );
};

export default App;