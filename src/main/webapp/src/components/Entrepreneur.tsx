import React from 'react';
import { Token } from './Token';
import { Link } from '../api/MonopolyApi';

export const Entrepreneur = (props: {
  name: string;
  tokenType: string;
  funds: number;
  square: any;
  human: boolean;
  rollLink: Link;
  clickHandler: (link: Link) => void;
}) => {

  return (
    <div className="Entrepreneur-cell">
      <div>
        <label>{ props.name }</label>
        { props.human && <img src="/star.png" alt="" width="30" height="28"/> }
      </div>
      <Token name={ props.tokenType }/>
      <div>
        <label>${ props.funds }</label>
      </div>
      <div>
        { props.human && props.rollLink &&
            <span>
            <button id={ 'button-roll' }
                    type="button"
                    className={ 'Roll-button-input' }
                    onClick={ () => props.clickHandler(props.rollLink) }>
              { props.rollLink.title ? props.rollLink.title : '' }
            </button>
          </span>
        }
      </div>
    </div>
  );
};