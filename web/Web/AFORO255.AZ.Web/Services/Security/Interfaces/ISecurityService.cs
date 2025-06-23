using AFORO255.AZ.Web.DTOs.Security;

namespace MS.AFORO255.Web.Service.Auth.Interfaces
{
    public interface ISecurityService
    {
        Task<SecurityDTOResponse> Login(SecurityDTORequest securityDTORequest);
    }
}
