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
      <label>{ props.name }</label>
      { props.human && <img src="/star.png" alt="" width="30" height="28"/> }
      <br/>
      <Token name={ props.tokenType } width={ 160 } height={ 163 }/>
      <br/>
      <label>${ props.funds }</label>
      { props.human && props.rollLink &&
          <span>
            <button id={ 'button-roll' }
                    type="button"
                    className={ 'Token-button-input' }
                    onClick={ () => props.clickHandler(props.rollLink) }>
              <span>{ props.rollLink.title ? props.rollLink.title : '' }</span>
            </button>
          </span>
      }
    </div>
  );
};