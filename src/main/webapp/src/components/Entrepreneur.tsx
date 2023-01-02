import React from 'react';
import { Token } from './Token';

export const Entrepreneur = (props: {
  name: string;
  tokenType: string;
  funds: number;
  square: any;
  human: boolean;
}) => {
  
  return (
    <div className="Entrepreneur-cell">
      <label>{ props.name }</label>
      { props.human && <img src="/star.png" alt="" width="30" height="28"/> }
      <br/>
      <Token name={props.tokenType} width={160} height={163} />
      <br/>
      <label>${ props.funds }</label>
    </div>
  );
};