import '../css/Monopoly.css';

import { useParams } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import { ApiErrors, DriveResource, getResource } from '../api/MonopolyApi';
import { Modal } from '../components/Modal';
import { Entrepreneur } from '../components/Entrepreneur';

export const Monopoly = () => {

  const { gameId } = useParams();
  const [driveResource, setDriveResource] = useState<DriveResource>();
  const [shouldShowModal, setShouldShowModal] = useState<boolean>(false);

  useEffect(() => {
    getResource('/api/monopoly/' + gameId)
      .then(response => {
        setDriveResource(response);
      });
  }, []);

  const resourceData = driveResource ? driveResource['data'] : {};
  const players: any[] = resourceData ? resourceData['players'] : [];
  const rows: React.ReactNode[] = players && players.map((player) => (
    <Entrepreneur name={ player['name'] }
                  tokenType={ player['tokenType'] }
                  funds={ player['funds'] }
                  square={ player['square'] }
    />
  ));

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
    </>
  );
};