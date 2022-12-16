import '../css/Main.css';
import '../css/MainTextField.css';
import '../css/MainOptionsBox.css';
import '../css/MainDateField.css';
import '../css/MainFieldGroupRow.css';
import '../css/MainTable.css';

import { HttpMethod, ApiErrors, deleteResource, DriveResource, getResource, Link, saveResource, updateResource } from "../api/MonopolyApi";
import { useEffect, useState } from 'react';
import { FormLayout } from '../components/FormLayout';
import { Modal } from '../components/Modal';
import { useNavigate } from 'react-router-dom';

export const Main = () => {
  const navigate = useNavigate();
  const [driveResource, setDriveResource] = useState<DriveResource>();
  const [errors, setErrors] = useState<ApiErrors | undefined>(undefined);
  const [shouldShowModal, setShouldShowModal] = useState<boolean>(false);

  const navigateToGame = (response: DriveResource) => {
    const data = response.data ? response.data : {};
    const gameId: string = data["id"];
    navigate('/monopoly/' + gameId);
  };

  useEffect(() => {
    getResource("/api/monopoly")
      .then(response => { setDriveResource(response) });
  }, [])

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
        .then(response => navigateToGame(response))
        .catch(error => handleErrors(error.response.data));
    }
  }

  const handleDataChange = (attributeName: string, attributeValue: any) => {
    const data = { ...driveResource?.data, [attributeName]: attributeValue };
    setDriveResource({ ...driveResource, id: Date.now(), data: data });
  }

  return (
    <>
      {errors && <Modal shouldShow={shouldShowModal} setShouldShow={setShouldShowModal}>
        <div>
          <label>
            {errors.description}
          </label>
        </div>
      </Modal>}
      {driveResource && <FormLayout key={driveResource.id}
                                    driveResource={driveResource}
                                    errors={errors}
                                    clickHandler={toggleClickHandler}
                                    dataChangeHandler={handleDataChange} />}
    </>
  );
}
