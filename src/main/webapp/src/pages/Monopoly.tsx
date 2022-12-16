import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { ApiErrors, DriveResource, getResource, HttpMethod, Link, saveResource } from '../api/MonopolyApi';
import { Modal } from '../components/Modal';
import { FormLayout } from '../components/FormLayout';

export const Monopoly = () => {

  const { gameId } = useParams();
  const [driveResource, setDriveResource] = useState<DriveResource>();
  const [errors, setErrors] = useState<ApiErrors | undefined>(undefined);
  const [shouldShowModal, setShouldShowModal] = useState<boolean>(false);

  useEffect(() => {
    getResource("/api/monopoly/" + gameId)
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
        .then(response => setDriveResource(response))
        .catch(error => handleErrors(error.response.data));
    }
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
                                    dataChangeHandler={() => {}} />}
    </>
  );
};