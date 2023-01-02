import React from 'react';

export const Token = (props: {
  name: string;
  width: number;
  height: number;
}) => {

  const renderTokenSwitch = (name: string, width: number, height: number): React.ReactNode => {
    let token: React.ReactNode;
    switch (name) {
      case 'BATTLESHIP':
        token = <img src="/battleship.png" alt="" width={width} height={height}/>;
        break;
      case 'battleship':
        token = <img src="/battleship.png" alt="" width={width} height={height}/>;
        break;
      case 'RACE_CAR':
        token = <img src="/racecar.png" alt="" width={width} height={height}/>;
        break;
      case 'car':
        token = <img src="/racecar.png" alt="" width={width} height={height}/>;
        break;
      case 'IRON':
        token = <img src="/iron.png" alt="" width={width} height={height}/>;
        break;
      case 'iron':
        token = <img src="/iron.png" alt="" width={width} height={height}/>;
        break;
      case 'THIMBLE':
        token = <img src="/thimble.png" alt="" width={width} height={height}/>;
        break;
      case 'thimble':
        token = <img src="/thimble.png" alt="" width={width} height={height}/>;
        break;
      case 'CANNON':
        token = <img src="/cannon.png" alt="" width={width} height={height}/>;
        break;
      case 'cannon':
        token = <img src="/cannon.png" alt="" width={width} height={height}/>;
        break;
      case 'BOOT':
        token = <img src="/boot.png" alt="" width={width} height={height}/>;
        break;
      case 'boot':
        token = <img src="/boot.png" alt="" width={width} height={height}/>;
        break;
      case 'TOP_HAT':
        token = <img src="/tophat.png" alt="" width={width} height={height}/>;
        break;
      case 'topHat':
        token = <img src="/tophat.png" alt="" width={width} height={height}/>;
        break;
      default:
        token = <div/>;
    }
    return token;
  };

  return (
    <>
      { renderTokenSwitch(props.name, props.width, props.height) }
    </>
  );
}