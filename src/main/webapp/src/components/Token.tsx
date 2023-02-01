import React from 'react';

export const Token = (props: {
  name: string;
}) => {

  const renderTokenSwitch = (name: string): React.ReactNode => {
    let token: React.ReactNode;
    switch (name) {
      case 'BATTLESHIP':
        token = <img src="/battleship.png" alt="" className={"Token-image"}/>;
        break;
      case 'battleship':
        token = <img src="/battleship.png" alt="" className={"Token-image"}/>;
        break;
      case 'RACE_CAR':
        token = <img src="/racecar.png" alt="" className={"Token-image"}/>;
        break;
      case 'car':
        token = <img src="/racecar.png" alt="" className={"Token-image"}/>;
        break;
      case 'IRON':
        token = <img src="/iron.png" alt="" className={"Token-image"}/>;
        break;
      case 'iron':
        token = <img src="/iron.png" alt="" className={"Token-image"}/>;
        break;
      case 'THIMBLE':
        token = <img src="/thimble.png" alt="" className={"Token-image"}/>;
        break;
      case 'thimble':
        token = <img src="/thimble.png" alt="" className={"Token-image"}/>;
        break;
      case 'CANNON':
        token = <img src="/cannon.png" alt="" className={"Token-image"}/>;
        break;
      case 'cannon':
        token = <img src="/cannon.png" alt="" className={"Token-image"}/>;
        break;
      case 'BOOT':
        token = <img src="/boot.png" alt="" className={"Token-image"}/>;
        break;
      case 'boot':
        token = <img src="/boot.png" alt="" className={"Token-image"}/>;
        break;
      case 'TOP_HAT':
        token = <img src="/tophat.png" alt="" className={"Token-image"}/>;
        break;
      case 'topHat':
        token = <img src="/tophat.png" alt="" className={"Token-image"}/>;
        break;
      default:
        token = <div/>;
    }
    return token;
  };

  return (
    <div>
      { renderTokenSwitch(props.name) }
    </div>
  );
}