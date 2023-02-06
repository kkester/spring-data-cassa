import React from 'react';
import { Token } from './Token';
import { apiHost } from '../api/MonopolyApi';

export const Square = (props: {
  id: number;
  name: string;
  owner: string;
  ownedType: string;
  visitors: string[];
  pot: number;
}) => {

  const renderSquareSwitch = (id: number): React.ReactNode => {
    return <img src={ apiHost + '/api/squares/' + id + '/image'} alt="" className="Property-image"/>;
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
        { renderSquareSwitch(props.id) }
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