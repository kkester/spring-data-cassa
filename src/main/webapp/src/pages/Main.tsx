import '../css/Main.css';

import { ApiErrors, DriveResource, getResource, HttpMethod, Link, saveResource } from '../api/MonopolyApi';
import React, { useEffect, useState } from 'react';
import { Modal } from '../components/Modal';
import { useNavigate } from 'react-router-dom';
import { Token } from '../components/Token';

export const Main = () => {
  const navigate = useNavigate();
  const [driveResource, setDriveResource] = useState<DriveResource>();
  const [errors, setErrors] = useState<ApiErrors | undefined>(undefined);
  const [shouldShowModal, setShouldShowModal] = useState<boolean>(false);

  const navigateToGame = (response: DriveResource) => {
    const data = response.data ? response.data : {};
    const gameId: string = data['id'];
    navigate('/monopoly/' + gameId);
  };

  useEffect(() => {
    getResource('/api/monopoly')
      .then(response => {
        setDriveResource(response);
      });
  }, []);

  const handleErrors = (apiErrors: ApiErrors) => {
    setErrors(apiErrors);
    setShouldShowModal(true);
  };

  const toggleClickHandler = (link: Link) => {
    setErrors(undefined);
    const linkMethod: HttpMethod = link.method ? link.method : HttpMethod.GET;
    if (HttpMethod.GET === linkMethod) {
      getResource(link.href)
        .then(response => setDriveResource(response))
        .catch(error => handleErrors(error.response.data));
    } else if (driveResource && HttpMethod.POST === linkMethod) {
      saveResource(link.href, driveResource.data)
        .then(response => navigateToGame(response))
        .catch(error => handleErrors(error.response.data));
    }
  };

  const links = driveResource && driveResource['links'] ? driveResource['links'] : {};

  const convertToButton = (linkName: string, link: Link, index: number, clickHandler: (link: Link) => void) => {
    return <div id={ 'button-' + index } className="Token-button">
      <button id={ 'button-' + index }
              type="button"
              className={ 'Token-button-input' }
              onClick={ () => clickHandler(link) }>
        <Token name={ linkName } width={ 160 } height={ 163 }/>
        <span>{ link.title ? link.title : '' }</span>
      </button>
    </div>;
  };

  return (
    <>
      { errors && <Modal shouldShow={ shouldShowModal } setShouldShow={ setShouldShowModal }>
        <div>
          <label>
            { errors.description }
          </label>
        </div>
      </Modal> }
      { links &&
          <div className={ 'Token-button-row' }>
            { Object.keys(links).map((linkName: string, i: number) =>
              convertToButton(linkName, links[linkName], i, toggleClickHandler)) }
          </div> }
    </>
  );
};
