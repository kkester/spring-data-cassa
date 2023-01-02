import React from 'react';
import { Token } from './Token';

export const Square = (props: {
  name: string;
  owner: string;
  ownedType: string;
  visitors: string[];
}) => {

  const renderSquareSwitch = (name: string): React.ReactNode => {
    let square: React.ReactNode;
    switch (name) {
      case 'Baltic Avenue':
        square = <img src="/baltic.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Community Chest':
        square = <img src="/chest.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Mediterranean Avenue':
        square = <img src="/medave.jpeg" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Income Tax':
        square = <img src="/intax.jpeg" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Reading Railroad':
        square = <img src="/reading.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Oriental Avenue':
        square = <img src="/oriental.jpeg" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Chance':
        square = <img src="/chance.jpeg" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Vermont Avenue':
        square = <img src="/vermont.jpeg" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Connecticut Avenue':
        square = <img src="/conn.jpeg" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Jail Just Visiting':
        square = <img src="/jail.jpeg" alt="" width="180" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'St Charles Place':
        square = <img src="/stcharles.jpeg" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Electric Company':
        square = <img src="/electric.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'States Avenue':
        square = <img src="/states.jpeg" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Virgina Avenue':
        square = <img src="/virgina.jpeg" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Pennsylvania Railroad':
        square = <img src="/pennrr.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'St James':
        square = <img src="/stjames.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Tennessee Avenue':
        square = <img src="/tennave.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'New York Avenue':
        square = <img src="/nyave.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Free Parking':
        square = <img src="/parking.jpeg" alt="" width="180" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Kentucky Avenue':
        square = <img src="/kentucky.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Indiana Avenue':
        square = <img src="/indiana.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Illinois Avenue':
        square = <img src="/illinois.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'B.&O. Railroad':
        square = <img src="/borr.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Atlantic Avenue':
        square = <img src="/atlantic.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Ventnor Avenue':
        square = <img src="/ventnor.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Water Works':
        square = <img src="/water.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'Marvin Gardens':
        square = <img src="/marvin.png" alt="" width="160" height="179" className="Entrepreneur-cell-item"/>;
        break;
      default:
        square = <img src="/go.jpeg" alt="" width="180" height="179" className="Entrepreneur-cell-item"/>;
    }
    return square;
  };

  const renderOwnerTypeSwitch = (ownedType: string): React.ReactNode => {
    let ownerTypeNode: React.ReactNode;
    switch (ownedType) {
      case 'FOR_SALE':
        ownerTypeNode = <img src="/forsale.jpeg" alt="" width="70" height="50"/>;
        break;
      case 'HOUSE':
        ownerTypeNode = <img src="/house.png" alt="" width="70" height="55"/>;
        break;
      case 'HOTEL':
        ownerTypeNode = <img src="/hotel.png" alt="" width="70" height="55"/>;
        break;
      default:
        ownerTypeNode = <div className="Square-item"/>;
    }
    return ownerTypeNode;
  };

  return (
    <div className="Square-cell">
      { renderOwnerTypeSwitch(props.ownedType) }
      <br/>
      { renderSquareSwitch(props.name) }
      <br/>
      <label>{ props.owner }</label>
      <br/>
      <>{
        props.visitors && props.visitors.map((token) => (
          <Token name={token} width={90} height={92} />
        ))
      }</>
    </div>
  );
};
;