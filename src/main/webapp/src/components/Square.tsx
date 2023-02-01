import React from 'react';
import { Token } from './Token';

export const Square = (props: {
  name: string;
  owner: string;
  ownedType: string;
  visitors: string[];
  pot: number;
}) => {

  const renderSquareSwitch = (name: string): React.ReactNode => {
    let square: React.ReactNode;
    switch (name) {
      case 'Baltic Avenue':
        square = <img src="/baltic.png" alt="" className="Property-image"/>;
        break;
      case 'Community Chest':
        square = <img src="/chest.png" alt="" className="Property-image"/>;
        break;
      case 'Mediterranean Avenue':
        square = <img src="/medave.jpeg" alt="" className="Property-image"/>;
        break;
      case 'Income Tax':
        square = <img src="/intax.jpeg" alt="" className="Property-image"/>;
        break;
      case 'Reading Railroad':
        square = <img src="/reading.png" alt="" className="Property-image"/>;
        break;
      case 'Oriental Avenue':
        square = <img src="/oriental.jpeg" alt="" className="Property-image"/>;
        break;
      case 'Chance':
        square = <img src="/chance.jpeg" alt="" className="Property-image"/>;
        break;
      case 'Vermont Avenue':
        square = <img src="/vermont.jpeg" alt="" className="Property-image"/>;
        break;
      case 'Connecticut Avenue':
        square = <img src="/conn.jpeg" alt="" className="Property-image"/>;
        break;
      case 'Jail Just Visiting':
        square = <img src="/jail.jpeg" alt="" className="Property-image"/>;
        break;
      case 'St Charles Place':
        square = <img src="/stcharles.jpeg" alt="" className="Property-image"/>;
        break;
      case 'Electric Company':
        square = <img src="/electric.png" alt="" className="Property-image"/>;
        break;
      case 'States Avenue':
        square = <img src="/states.jpeg" alt="" className="Property-image"/>;
        break;
      case 'Virgina Avenue':
        square = <img src="/virgina.jpeg" alt="" className="Property-image"/>;
        break;
      case 'Pennsylvania Railroad':
        square = <img src="/pennrr.png" alt="" className="Property-image"/>;
        break;
      case 'St James':
        square = <img src="/stjames.png" alt="" className="Property-image"/>;
        break;
      case 'Tennessee Avenue':
        square = <img src="/tennave.png" alt="" className="Property-image"/>;
        break;
      case 'New York Avenue':
        square = <img src="/nyave.png" alt="" className="Property-image"/>;
        break;
      case 'Free Parking':
        square = <img src="/parking.jpeg" alt="" className="Property-image"/>;
        break;
      case 'Kentucky Avenue':
        square = <img src="/kentucky.png" alt="" className="Property-image"/>;
        break;
      case 'Indiana Avenue':
        square = <img src="/indiana.png" alt="" className="Property-image"/>;
        break;
      case 'Illinois Avenue':
        square = <img src="/illinois.png" alt="" className="Property-image"/>;
        break;
      case 'B.&O. Railroad':
        square = <img src="/borr.png" alt="" className="Property-image"/>;
        break;
      case 'Atlantic Avenue':
        square = <img src="/atlantic.png" alt="" className="Property-image"/>;
        break;
      case 'Ventnor Avenue':
        square = <img src="/ventnor.png" alt="" className="Property-image"/>;
        break;
      case 'Water Works':
        square = <img src="/water.png" alt="" className="Property-image"/>;
        break;
      case 'Marvin Gardens':
        square = <img src="/marvin.png" alt="" className="Property-image"/>;
        break;
      default:
        square = <img src="/go.jpeg" alt="" className="Property-image"/>;
    }
    return square;
  };

  const renderOwnerTypeSwitch = (ownedType: string): React.ReactNode => {
    let ownerTypeNode: React.ReactNode;
    switch (ownedType) {
      case 'FOR_SALE':
        ownerTypeNode = <img src="/forsale.png" alt="" className={'Owner-image'}/>;
        break;
      case 'HOUSE':
        ownerTypeNode = <img src="/house.png" alt="" className={'Owner-image'}/>;
        break;
      case 'HOTEL':
        ownerTypeNode = <img src="/hotel.png" alt="" className={'Owner-image'}/>;
        break;
      default:
        ownerTypeNode = <div/>;
    }
    return ownerTypeNode;
  };

  return (
    <div className="Square-cell">
      <div>
        { renderOwnerTypeSwitch(props.ownedType) }
      </div>
      <div>
        { renderSquareSwitch(props.name) }
      </div>
      <div>
        <label>{ props.owner }</label>
        { props.name === 'Free Parking' && <label>Pot: ${ props.pot }</label> }
      </div>
      <div className={ 'Square-token-row' }>{
        props.visitors && props.visitors.map((token) => (
          <Token name={ token }/>
        ))
      }</div>
    </div>
  );
};