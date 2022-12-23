import React from 'react';

export const Square = (props: {
  name: string;
  owner: string;
  ownedType: string;
  visitors: string[];
}) => {

  const renderTokenSwitch = (name: string): React.ReactNode => {
    let token: React.ReactNode;
    switch (name) {
      case 'BATTLESHIP':
        token = <img src="/battleship.jpeg" alt="" width="90" height="92"/>;
        break;
      case 'RACE_CAR':
        token = <img src="/racecar.jpeg" alt="" width="90" height="92"/>;
        break;
      case 'IRON':
        token = <img src="/iron.jpeg" alt="" width="90" height="92"/>;
        break;
      case 'THIMBLE':
        token = <img src="/thimble.jpeg" alt="" width="90" height="92"/>;
        break;
      case 'CANNON':
        token = <img src="/cannon.jpeg" alt="" width="90" height="92"/>;
        break;
      case 'BOOT':
        token = <img src="/boot.jpeg" alt="" width="90" height="92"/>;
        break;
      case 'TOP_HAT':
        token = <img src="/tophat.jpeg" alt="" width="90" height="92"/>;
        break;
      default:
        token = <div/>;
    }
    return token;
  };

  const renderSquareSwitch = (name: string): React.ReactNode => {
    let square: React.ReactNode;
    switch (name) {
      case 'Baltic Avenue':
        square = <img src="/baltic.png" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Community Chest':
        square = <img src="/chest.png" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Mediterranean Avenue':
        square = <img src="/medave.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Income Tax':
        square = <img src="/intax.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Reading Railroad':
        square = <img src="/reading.png" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
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
      case 'Jail Just Visiting':
        square = <img src="/jail.jpeg" alt="" width="180" height="179" className="Entrepreneur-cell-item"/>;
        break;
      case 'St Charles Place':
        square = <img src="/stcharles.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Electric Company':
        square = <img src="/electric.png" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'States Avenue':
        square = <img src="/states.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Virgina Avenue':
        square = <img src="/virgina.jpeg" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Pennsylvania Railroad':
        square = <img src="/pennrr.png" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'St James':
        square = <img src="/stjames.png" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'Tennessee Avenue':
        square = <img src="/tennave.png" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
        break;
      case 'New York Avenue':
        square = <img src="/nyave.png" alt="" width="160" height="224" className="Entrepreneur-cell-item"/>;
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
        ownerTypeNode = <div className="SquareCell"><label>$$ For Sale $$</label></div>;
        break;
      case 'HOUSE':
        ownerTypeNode = <img src="/house.png" alt="" width="75" height="61"/>;
        break;
      case 'HOTEL':
        ownerTypeNode = <img src="/hotel.png" alt="" width="75" height="61"/>;
        break;
      default:
        ownerTypeNode = <div className="Square-item"/>;
    }
    return ownerTypeNode;
  };

  const visitorCells: React.ReactNode[] = props.visitors && props.visitors.map((token) => (
    renderTokenSwitch(token)
  ));

  return (
    <div className="Square-cell">
      <br/>
      { renderOwnerTypeSwitch(props.ownedType) }
      <br/>
      { renderSquareSwitch(props.name) }
      <br/>
      <label>{ props.owner }</label>
      <br/>
      <label>{ visitorCells }</label>
    </div>
  );
};
;