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

  return (
    <div className="Entrepreneur-cell">
      <label>{ props.name }</label>
      { props.human && <img src="/star.png" alt="" width="30" height="28"/> }
      <br/>
      { renderTokenSwitch(props.tokenType) }
      <br/>
      <label>${ props.funds }</label>
    </div>
  );
};