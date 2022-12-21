import React from 'react';

export const Entrepreneur = (props: {
  name: string;
  tokenType: string;
  funds: number;
  square: any;
  human: boolean;
}) => {

  const renderTokenSwitch = (name: string): React.ReactNode => {
    let token: React.ReactNode;
    switch (name) {
      case 'BATTLESHIP':
        token = <img src="/battleship.jpeg" alt="" width="180" height="183"/>;
        break;
      case 'RACE_CAR':
        token = <img src="/racecar.jpeg" alt="" width="180" height="183"/>;
        break;
      case 'IRON':
        token = <img src="/iron.jpeg" alt="" width="180" height="183"/>;
        break;
      case 'THIMBLE':
        token = <img src="/thimble.jpeg" alt="" width="180" height="183"/>;
        break;
      case 'CANNON':
        token = <img src="/cannon.jpeg" alt="" width="180" height="183"/>;
        break;
      case 'BOOT':
        token = <img src="/boot.jpeg" alt="" width="180" height="183"/>;
        break;
      default:
        token = <img src="/tophat.jpeg" alt="" width="180" height="183"/>;
    }
    return token;
  };

  const renderSquareSwitch = (name: string): React.ReactNode => {
    let square: React.ReactNode;
    switch (name) {
      case 'Baltic Avenue':
        square = <img src="/baltic.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Community Chest':
        square = <img src="/chest.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Mediterranean Avenue':
        square = <img src="/medave.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Income Tax':
        square = <img src="/intax.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Reading Railroad':
        square = <img src="/reading.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Oriental Avenue':
        square = <img src="/oriental.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Chance':
        square = <img src="/chance.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Vermont Avenue':
        square = <img src="/vermont.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Connecticut Avenue':
        square = <img src="/conn.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      default:
        square = <img src="/go.jpeg" alt="" width="180" height="179" className="Entrepreneur-cell-item"/>;
    }
    return square;
  };

  return (
    <div className="Entrepreneur-cell">
      <label>{ props.name }</label>
      { props.human && <img src="/star.png" alt="" width="30" height="28"/> }
      <br/>
      { renderTokenSwitch(props.tokenType) }
      <br/>
      <label>${ props.funds }</label>
      <br/>
      { renderSquareSwitch(props.square['name']) }
      <br/>
      <label>{ props.square['ownedType'] }</label>
      <br/>
      <label>{ props.square['owner'] }</label>
    </div>
  );
};