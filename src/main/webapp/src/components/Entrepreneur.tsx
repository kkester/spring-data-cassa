export const Entrepreneur = (props: {
  name: string;
  tokenType: string;
  funds: number;
  square: string;
}) => {

  return (
    <div className="Entrepreneur-cell">
      <label>{ props.name }</label>
      <br/>
      { props.tokenType === 'BATTLESHIP' &&
          <img src="/battleship.jpeg" alt="" width="180" height="183"/>
      }
      { props.tokenType === 'RACE_CAR' &&
          <img src="/racecar.jpeg" alt="" width="180" height="183"/>
      }
      { props.tokenType === 'IRON' &&
          <img src="/iron.jpeg" alt="" width="180" height="183"/>
      }
      { props.tokenType === 'TOP_HAT' &&
          <img src="/tophat.jpeg" alt="" width="180" height="183"/>
      }
      { props.tokenType === 'THIMBLE' &&
          <img src="/thimble.jpeg" alt="" width="180" height="183"/>
      }
      { props.tokenType === 'CANNON' &&
          <img src="/cannon.jpeg" alt="" width="180" height="183"/>
      }
      { props.tokenType === 'BOOT' &&
          <img src="/boot.jpeg" alt="" width="180" height="183"/>
      }
      <br/>
      <label>${ props.funds }</label>
      <br/>
      { props.square === 'GO' &&
          <img src="/go.jpeg" alt="" width="180" height="183" className="Entrepreneur-cell-item"/>
      }
      { props.square === 'Baltic Avenue' &&
          <img src="/baltic.jpeg" alt="" width="180" height="183" className="Entrepreneur-cell-item"/>
      }
      { props.square === 'Oriental Avenue' &&
          <img src="/oriental.jpeg" alt="" width="180" height="183" className="Entrepreneur-cell-item"/>
      }
    </div>
  );
};