package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeService implements IDao<Employe> {
    private SessionFactory sessionFactory;

    public EmployeService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean create(Employe o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Employe o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.update(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Employe o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.delete(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Employe findById(int id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Employe.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Employe> findAll() {
        Session session = sessionFactory.openSession();
        try {
            Query<Employe> query = session.createQuery("FROM Employe", Employe.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Tache> getTachesByEmploye(int employeId) {
        Session session = sessionFactory.openSession();
        try {
            Query<Tache> query = session.createQuery(
                    "SELECT DISTINCT et.tache FROM EmployeTache et WHERE et.employe.id = :employeId",
                    Tache.class
            );
            query.setParameter("employeId", employeId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Projet> getProjetsByEmploye(int employeId) {
        Session session = sessionFactory.openSession();
        try {
            Query<Projet> query = session.createQuery(
                    "SELECT p FROM Projet p WHERE p.chef.id = :employeId",
                    Projet.class
            );
            query.setParameter("employeId", employeId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public Employe findByMatricule(String matricule) {
        Session session = sessionFactory.openSession();
        try {
            Query<Employe> query = session.createQuery("FROM Employe WHERE matricule = :matricule", Employe.class);
            query.setParameter("matricule", matricule);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}