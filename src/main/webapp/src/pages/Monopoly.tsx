import '../css/Monopoly.css';

import { useParams } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import { ApiErrors, DriveResource, getResource, HttpMethod, Link, saveResource } from '../api/MonopolyApi';
import { Modal } from '../components/Modal';
import { Entrepreneur } from '../components/Entrepreneur';
import { ButtonGroupRow } from '../components/ButtonGroupRow';

export const Monopoly = () => {

  const { gameId } = useParams();
  const [driveResource, setDriveResource] = useState<DriveResource>();
  const [errors, setErrors] = useState<ApiErrors | undefined>(undefined);
  const [shouldShowModal, setShouldShowModal] = useState<boolean>(false);

  useEffect(() => {
    getResource('/api/monopoly/' + gameId)
      .then(response => {
        setDriveResource(response);
      });
  }, []);

  const handleErrors = (apiErrors: ApiErrors) => {
    setErrors(apiErrors);
    setShouldShowModal(true);
  }

  const toggleClickHandler = (link: Link) => {
    setErrors(undefined);
    const linkMethod: HttpMethod = link.method ? link.method : HttpMethod.GET;
    if (HttpMethod.GET === linkMethod) {
      getResource(link.href)
        .then(response => setDriveResource(response))
        .catch(error => handleErrors(error.response.data));
    } else if (driveResource && HttpMethod.POST === linkMethod) {
      saveResource(link.href, driveResource.data)
        .then(response => setDriveResource(response))
        .catch(error => handleErrors(error.response.data));
    }
  }

  const resourceData = driveResource ? driveResource['data'] : {};
  const players: any[] = resourceData ? resourceData['players'] : [];
  const rows: React.ReactNode[] = players && players.map((player) => (
    <Entrepreneur name={ player['name'] }
                  tokenType={ player['tokenType'] }
                  funds={ player['funds'] }
                  square={ player['square'] }
                  human={ player['human'] }
    />
  ));

  const links = driveResource && driveResource.links ? driveResource.links : {};
  const navLinks: Link[] = Object.keys(links).map((linkName) => links[linkName]);

  return (
    <>
      { shouldShowModal && <Modal shouldShow={ shouldShowModal } setShouldShow={ setShouldShowModal }>
        <div>
          <label>Message TBD</label>
        </div>
      </Modal> }
      <div className="Entrepreneur-container">
        { rows }
      </div>
      <ButtonGroupRow links={navLinks} clickHandler={toggleClickHandler} />
    </>
  );
};