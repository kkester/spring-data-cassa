import '../css/Monopoly.css';

import { useParams } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import { ApiErrors, DriveResource, getResource, HttpMethod, Link, saveResource } from '../api/MonopolyApi';
import { Modal } from '../components/Modal';
import { Entrepreneur } from '../components/Entrepreneur';
import { Square } from '../components/Square';

export const Monopoly = () => {

  const { gameId } = useParams();
  const [driveResource, setDriveResource] = useState<DriveResource>();
  const [errors, setErrors] = useState<ApiErrors | undefined>(undefined);
  const [shouldShowModal, setShouldShowModal] = useState<boolean>(false);
  const [messages, setMessages] = useState<string[] | undefined>(undefined);

  useEffect(() => {
    getResource('/api/monopoly/' + gameId)
      .then(response => handleMessages(response))
      .then(response => setDriveResource(response));
  }, []);

  const handleErrors = (apiErrors: ApiErrors) => {
    setErrors(apiErrors);
    setShouldShowModal(true);
  };

  const handleMessages = (newResource: DriveResource): DriveResource => {
    const newPlayers: any[] = newResource['data'] ? newResource['data']['players'] : [];
    const newMessages: string[] = newPlayers && newPlayers
      .filter(player => player['message'])
      .map(player => player['name'] + ', ' + player['message']);
    setMessages(newMessages);
    if (newMessages && newMessages.length > 0) {
      setShouldShowModal(true);
    }
    return newResource;
  };

  const toggleClickHandler = (link: Link) => {
    setErrors(undefined);
    const linkMethod: HttpMethod = link.method ? link.method : HttpMethod.GET;
    if (HttpMethod.GET === linkMethod) {
      getResource(link.href)
        .then(response => handleMessages(response))
        .then(response => setDriveResource(response))
        .catch(error => handleErrors(error.response.data));
    } else if (driveResource && HttpMethod.POST === linkMethod) {
      saveResource(link.href, driveResource.data)
        .then(response => handleMessages(response))
        .then(response => setDriveResource(response))
        .catch(error => handleErrors(error.response.data));
    }
  };

  const resourceData = driveResource ? driveResource['data'] : {};
  const players: any[] = resourceData ? resourceData['players'] : [];
  const links = driveResource && driveResource.links ? driveResource.links : {};
  const playerCells: React.ReactNode[] = players && players.map((player) =>
    <Entrepreneur name={ player['name'] }
                  tokenType={ player['tokenType'] }
                  funds={ player['funds'] }
                  square={ player['square'] }
                  human={ player['human'] }
                  rollLink={ links['roll'] }
                  clickHandler={ toggleClickHandler }
    />
  );

  const squares: any[] = resourceData ? resourceData['squares'] : [];
  const squareCells: React.ReactNode[] = squares && squares.map((square) =>
    <Square name={ square['name'] }
            owner={ square['owner'] }
            ownedType={ square['ownedType'] }
            visitors={ square['visitors'] }
            pot={ resourceData && resourceData['pot'] }
    />
  );

  const messageElements = messages && messages.map(message => <div><label>{message}</label><br/></div>);

  return (
    <>
      { shouldShowModal && <Modal shouldShow={ shouldShowModal } setShouldShow={ setShouldShowModal }>
        <div>
          { messageElements }
        </div>
      </Modal> }
      <div className="Monopoly-container">
        <div className="Entrepreneur-container">
          { playerCells }
        </div>
        <div className="Squares-container">
          { squareCells }
        </div>
      </div>
    </>
  );
};